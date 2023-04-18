package capstone.ApplePie_Spring.Team.service;

import capstone.ApplePie_Spring.Team.domain.Member;
import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.Team.dto.MemberDto;
import capstone.ApplePie_Spring.Team.repository.MemberRepository;
import capstone.ApplePie_Spring.Team.repository.TeamRepository;
import capstone.ApplePie_Spring.Team.repository.VolunteerRepository;
import capstone.ApplePie_Spring.Team.resposne.ResponseMember;
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
            return new ResponseMember(ExceptionCode.VOLUNTEER_FIND_NOT);
        }

        Volunteer volunteer = findVolunteer.get();
        Team team = teamRepository.findByBoardIdAndStatus(volunteer.getTeam().getId(), STATUS).get();

        Member member = Member.builder()
                .team(team)
                .volunteer(volunteer)
                .build();
        memberRepository.save(member);
        team.addMember(member); // 모집 인원 수 감소
        return new ResponseMember(ExceptionCode.MEMBER_OK);
    }
}
