package com.jiashn.proxy.proxy.cglib.principle;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangjs
 * @date 2023-03-28 6:10
 * @description
 */
@Slf4j
public class Target {

    public void save(){
        log.info("save无参方法......");
    }
    public void save(int i){
        log.info("save有参i方法......："+i);
    }
    public void save(long j){
        log.info("save有参j方法......："+j);
    }
}
