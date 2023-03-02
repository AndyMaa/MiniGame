package minigame.core;

import java.util.Arrays;

public class Util {
    private final static char[] charMap={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0', '1',
    '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     *
     * @return 压缩后的字符串
     */
    public static String zipAddress(String ip,int port){
        String[] childs=ip.split("\\.");
        int size=charMap.length;
        int node;
        char[] rawOutPut=new char[9];
        //序列化ip
        for (int i=0;i<4;i++){
            node=Integer.parseInt(childs[i]);
            //进位放标志位
            if (node>=size){
                //放在标志位高位
                if (i%2==0){
                    rawOutPut[i<2?0:3]+=node/size*10;
                }else { //放低位
                    rawOutPut[i<2?0:3]+=node/size;
                }
            }

            rawOutPut[i<2?i+1:i+2]= (char) (node%size);
        }
        //序列化端口
        rawOutPut[6]= (char) (port/size/size);
        rawOutPut[7]= (char) (port%(size*size)/size);
        rawOutPut[8]= (char) (port%size);
        System.out.println(Arrays.toString(rawOutPut));
        for (int i=0;i<rawOutPut.length;i++){
            rawOutPut[i]=charMap[rawOutPut[i]];
        }
        return new String(rawOutPut);
    }

    /**
     *
     * @param zipped
     * @return 第一个是String ip，第二个是Integer port
     */
    public static Object[] unZipAddress(String zipped){
        char[] chars=zipped.toCharArray();
        int size= charMap.length;
        int p1=chars[0]/10*size+chars[1];
        int p2=chars[0]%10*size+chars[2];
        int p3=chars[3]/10*size+chars[4];
        int p4=chars[3]%10*size+chars[5];
        Object[] out=new Object[2];
        out[0]=p1+"."+p2+"."+p3+"."+p4;
        out[1]=chars[6]*size*size+chars[7]*size+chars[8];
        return out;
    }
}
