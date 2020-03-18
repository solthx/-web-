package homework.todolist;

import homework.todolist.response.CommenResultType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czf
 * @Date 2020/3/18 8:16 下午
 */
@RequestMapping("/api")
@Controller
public class Api {

    private static ConcurrentHashMap<Integer, CommenResultType> dataMap = new ConcurrentHashMap<>();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 返回所有任务
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<CommenResultType> getlist(){
        List<CommenResultType> commenResultTypeList = new ArrayList<>();
        for(Map.Entry<Integer, CommenResultType> entry:dataMap.entrySet())
            commenResultTypeList.add(entry.getValue());
        return commenResultTypeList;
    }

    /**
     * 增加一个任务
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public String addTask(){
        Integer id = atomicInteger.addAndGet(1);
        dataMap.put(id, new CommenResultType(id, "Restful API homework", new Date()));
        return "添加任务成功！";
    }


    /**
     * 删除一个任务
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/{id}")
    public String deleteTask(@PathVariable Integer id){
        if (dataMap.containsKey(id)){
            dataMap.remove(id);
            return "删除id="+id.toString()+"的任务成功！";
        }
        return "不存在该任务";
    }

    /**
     * 返回指定id的任务
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{id}")
    public CommenResultType getTask(@PathVariable Integer id){
        return dataMap.getOrDefault(id, null);
    }

}
