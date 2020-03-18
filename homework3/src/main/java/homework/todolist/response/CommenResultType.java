package homework.todolist.response;

import lombok.Data;

import java.util.Date;

/**
 * @author czf
 * @Date 2020/3/18 11:30 下午
 */
@Data
public class CommenResultType {
    Integer id;
    String content;
    Date createdTime;

    public CommenResultType(Integer id, String content, Date createdTime) {
        this.id = id;
        this.content = content;
        this.createdTime = createdTime;
    }
}
