package bean;

import lombok.Data;

/**
 * DATE: 16/7/20 23:20 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Data
public class User {
    private Long id;

    private String name ;

    private Integer type;

    private Integer status;

    private String mobile;
}
