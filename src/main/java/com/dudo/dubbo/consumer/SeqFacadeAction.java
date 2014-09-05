/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dudo.dubbo.consumer;

import com.billing.internalcontract.BaseResp;
import com.billing.internalcontract.other.ISeqFacade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SeqFacadeAction {

    private ISeqFacade seqFacade;
    private long start = 0;
    private long end = 0;


    public void setSeqFacade(ISeqFacade seqFacade) {
        this.seqFacade = seqFacade;
    }

    public void start() throws Exception {
        start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            try {
                BaseResp baseResp = seqFacade.nextSeq("");
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + baseResp);
            } catch (Exception e) {
                e.printStackTrace();
                if (end == 0) {
                    end = System.currentTimeMillis();
                }
                System.out.println("time:" + (end - start));
                System.out.println("count:" + (i));
                System.out.println("count/time:" + (1.0 * i / (end - start) * 1000));
            }
//            Thread.sleep(2000);
        }
    }

}