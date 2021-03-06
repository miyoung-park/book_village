package com.mi.bookvillage.common.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class UserVO {


    private int userSeq;

    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // @JsonIgnore 를 동적으로 처리할 수 있는 방법
    private String userPw;

    private String userName;

    private String userBirth;

    private String userTell;

    private String userRegDt;

    private String userUpdateDt;

    private String userRole;

    private int userPoint;


}
