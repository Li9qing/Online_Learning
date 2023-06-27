package edu.hubu.user.utils;

import edu.hubu.common.utils.R;

import java.util.List;

import static edu.hubu.user.utils.ResultCode.ERROR;

public class Result {
    public static R DUPlicate() {
        return R.error(500,"diplicate data!");
    }
    public static R ERRtoken(){
        return R.error(ERROR,"no token!");

    }
    public static R EMPTYdata(){

        return R.error(404,"no data!").put("count",0);
    }
    public static R BOOLresult(boolean var) {
        return R.ok().put("result", var);
    }
    public static<T> R PatchListResult(List<T> re){
        R u1 = R.ok();
        u1.put("Result",re);
        u1.put("count",re.size());
        return u1;
    }

}
