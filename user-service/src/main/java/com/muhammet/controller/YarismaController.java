package com.muhammet.controller;

import com.muhammet.dto.request.ActiveRequestDto;
import com.muhammet.dto.request.AnswerRequestDto;
import com.muhammet.dto.response.GetQuestionResponseDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserManagerException;
import com.muhammet.repository.entity.Active;
import com.muhammet.repository.entity.Answer;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.ActiveService;
import com.muhammet.service.AnswerService;
import com.muhammet.service.UserProfileService;
import com.muhammet.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/yarisma")
@RequiredArgsConstructor
public class YarismaController {

    private final JwtTokenManager jwtTokenManager;
    private final UserProfileService userProfileService;
    private final AnswerService answerService;

    private final ActiveService activeService;

    @GetMapping("/getquestion")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<GetQuestionResponseDto> getQuestion(){
        Active active = activeService.findByQuestionid("1");
        if(active==null){
            ResponseEntity.ok(GetQuestionResponseDto.builder().build());
        }
        if(active.getId()==null){
            ResponseEntity.ok(GetQuestionResponseDto.builder().build());
        }
        GetQuestionResponseDto dto = GetQuestionResponseDto.builder()
                .id("1")
                .question("Aşağıdakilerden hangisi bir programlama dili değildir?")
                .a("Java")
                .b("Ji#")
                .c("C++")
                .d("Phyton")
                .build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/active")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Void> activeQuestion(@RequestBody ActiveRequestDto dto){
        activeService.setiActive(dto.getQuestionid(),true);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/passive")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Void> passiveQuestion(@RequestBody ActiveRequestDto dto){
        activeService.setiActive(dto.getQuestionid(),false);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/answer")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Void> getAnswer(@RequestBody AnswerRequestDto dto){
        try{
            Optional<Long> authid  =jwtTokenManager.getUserId(dto.getToken());
            if(authid.isEmpty())  throw new UserManagerException(ErrorType.INVALID_TOKEN);
            UserProfile user =  userProfileService.findByAuthId(authid.get());
            answerService.save(dto,user);
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    @GetMapping("/getresult")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<List<Answer>> getScore(){
     return ResponseEntity.ok(answerService.findByQuestionid("1"));
    }
}
