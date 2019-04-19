package com.dapeng.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dapeng.customer.utils.ConvertPicToVedio;
import com.dapeng.customer.utils.GeneratePatten;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }

//    @GetMapping(value = "/gotoUser")
    @RequestMapping(value = "/gotoUser")
    @ResponseBody
    public Map<String,Object> getUser(@RequestParam Integer id){
        Map<String,Object> data = new HashMap<>();
        data = restTemplate.getForObject("http://service-provider/getUser?id="+id,Map.class);
        return data;
    }

    @RequestMapping(value = "getImage")
    @ResponseBody
    public Map<String,String> getImage(){
        GeneratePatten generatePatten = new GeneratePatten();
        generatePatten.setBasePicName("D:\\background.png");
        generatePatten.setMp3Path("E:\\03environment\\ffmpeg\\bin\\test3.mp3");
        generatePatten.setSavePath("D:\\files\\");
        generatePatten.setSinglePageTextNum(50);
        generatePatten.setSuffix("png");
        generatePatten.setTextMessage("我对未来和远方几乎淡漠了。\n" +
                "我只去过几次哈尔滨，\n" +
                "第一次，是送去南方上学的儿子，\n" +
                "我第一次看见飞机，我以为\n" +
                "它一直在那里等候我们，\n" +
                "后来我才知道它是前30分钟飞来的。\n" +
                "儿子在安检口向我招手，我木讷。\n" +
                "我夹杂在穿行的旅人里与儿子道别，\n" +
                "那是他第一次离开我们，去很远的地方。\n" +
                "刚刚降落的空少和空姐神采奕奕，\n" +
                "拉着拉杆箱，从我身旁走过\n" +
                "仿佛永远那么帅气漂亮。\n" +
                "我一年去几趟县城办事。\n" +
                "买种子化肥，和换二代身份证，\n" +
                "我上面头像一年老过一年。\n" +
                "时间已经把这个人碾碎。\n" +
                "现在他呈粉末状，格外细腻柔软。\n" +
                "从小村到小镇，我只有这么\n" +
                "一丁点的地方。我的庭院，\n" +
                "二月的末尾，乍暖还寒，\n" +
                "还在荒芜之中，但我仍感到\n" +
                "万物正在苏醒，我的葡萄藤蔓\n" +
                "闪闪发亮，根系在尘土里，\n" +
                "它正把黑暗抓得更紧。\n" +
                "你说，“有机会出来走走吧。”\n" +
                "我说“会的”。年轻时，\n" +
                "我想去爱尔兰，手插裤兜\n" +
                "走过都柏林忧伤的街，\n" +
                "像布鲁姆和斯蒂芬，\n" +
                "那时，我读詹姆斯.乔伊斯的\n" +
                "《尤利西斯》。我读梵高先生，\n" +
                "就向往北布拉班特的麦田和鸦群，\n" +
                "我亲爱的提奥，如果你健在，\n" +
                "哥哥一定把你资助的钱十倍奉还。\n" +
                "小小的荷兰，盛产郁金香，也出艺术家\n" +
                "伦勃朗被梵高的光彩已然遮蔽了。\n" +
                "可是，安默斯特你真是太远了！\n" +
                "不然我真想去那小住几日。去你家，\n" +
                "据说现已改为“壳”牌加油站。\n" +
                "去看你的小书桌，我惊叹，\n" +
                "你就是在方寸的书桌上写下不朽的诗？\n" +
                "我坐在安默斯特的小咖啡馆，看到这儿\n" +
                "来的游客，他们都像我吧，为你慕名而来；\n" +
                "我想你在你孤独的花园里采撷，\n" +
                "准备制作天竺葵的标本。\n" +
                "“篱笆那边的野草莓”\n" +
                "嗯，狄金森，忍不住我想乐。\n" +
                "现在，我想最宜居的地方是英国，\n" +
                "法国浪漫的轻浮；罗马，一座寂寞之都。\n" +
                "英国，有莎士比亚也有勃朗特姐妹，\n" +
                "有剑桥，也有足球流氓，有绅士也有穷人，\n" +
                "有乡下的素朴，也有海岛把我们隔开…");
//        restTemplate.getForObject()
        ResponseEntity<Map> ImageList = restTemplate.postForEntity("http://service-provider/getImage",generatePatten,Map.class);
//        ImageList.keySet().stream().map(c->ImageList.get(c)).forEach(System.out::println);

        return null;
    }


    @RequestMapping(value = "convertPicToVideo")
    @ResponseBody
    public Map<String,String> convertPicToVideo(){
        ConvertPicToVedio convertPicToVedio = new ConvertPicToVedio();
        convertPicToVedio.setFfmpegPath("E:\\03environment\\ffmpeg\\bin\\ffmpeg.exe");
        convertPicToVedio.setMp3path("E:\\03environment\\ffmpeg\\bin\\test3.mp3");
        convertPicToVedio.setTextpath("E:\\03environment\\ffmpeg\\bin\\test.txt");
        convertPicToVedio.setOutputpath("D:\\test4.mp4");
        ResponseEntity<Map> vedio = restTemplate.postForEntity("http://service-picToVedio/convertPicToVideo",convertPicToVedio,Map.class);
        return null;
    }

    @RequestMapping(value = "testMessage")
    public String getTestMessage(){
       String data = restTemplate.getForObject("http://service-picToVedio/testMessage",String.class);
       return data;
    }


}
