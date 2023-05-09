package capstone.ApplePie_Spring.Team.service;

import capstone.ApplePie_Spring.Team.domain.Member;
import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.Team.dto.MemberDto;
import capstone.ApplePie_Spring.Team.repository.MemberRepository;
import capstone.ApplePie_Spring.Team.repository.TeamRepository;
import capstone.ApplePie_Spring.Team.repository.VolunteerRepository;
import capstone.ApplePie_Spring.Team.resposne.ResponseMember;
import capstone.ApplePie_Spring.Team.resposne.ResponseTeam;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final Integer STATUS = 1;

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public Object save(MemberDto memberDto) {
        Optional<Volunteer> findVolunteer= volunteerRepository.findByIdAndStatus(memberDto.getVolunteerId(), STATUS);
        if (findVolunteer.isEmpty()) {
            return new ResponseTeam(ExceptionCode.VOLUNTEER_FIND_NOT);
        }

        Volunteer volunteer = findVolunteer.get();
        Optional<Team> findTeam = teamRepository.findByIdAndStatus(volunteer.getTeam().getId(), STATUS);
        if (findTeam.isEmpty()) {
            return new ResponseTeam(ExceptionCode.TEAM_FIND_NOT);
        }

        Team team = findTeam.get();
        Member member = Member.builder()
                .team(team)
                .volunteer(volunteer)
                .build();
        memberRepository.save(member);
        team.addMember(member);
        volunteer.setVolunteerStatus(Volunteer.VolunteerStatus.COMPLETE);

        // 모든 인원수가 이상인 경우에 complete 처리
        for (int n : team.getCount()) {
            if (n > 0) {
                return new ResponseMember(ExceptionCode.MEMBER_OK, team.getCount());
            }
        }
        team.setTeamStatus(Team.TeamStatus.COMPLETE);
        return new ResponseTeam(ExceptionCode.TEAM_COMPLETE);
    }

    public Object delete(MemberDto memberDto) {
        Optional<Volunteer> findVolunteer= volunteerRepository.findByIdAndStatus(memberDto.getVolunteerId(), STATUS);
        if (findVolunteer.isEmpty()) {
            return new ResponseTeam(ExceptionCode.VOLUNTEER_FIND_NOT);
        }

        Volunteer volunteer = findVolunteer.get();
        Optional<Team> findTeam = teamRepository.findByBoardIdAndStatus(volunteer.getTeam().getId(), STATUS);
        if (findTeam.isEmpty()) {
            return new ResponseTeam(ExceptionCode.TEAM_FIND_NOT);
        }

        Team team = findTeam.get();
        Member member = Member.builder()
                .team(team)
                .volunteer(volunteer)
                .build();
        memberRepository.save(member);
        team.removeMember(member); // 모집 인원 수 감소 ?
        volunteer.setVolunteerStatus(Volunteer.VolunteerStatus.COMPLETE);

        // 모든 인원수가 이상인 경우에 complete 처리

        return new ResponseTeam(ExceptionCode.MEMBER_OK);
    }
}
