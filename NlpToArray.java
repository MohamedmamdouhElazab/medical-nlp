/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.to.array;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author elazab
 */
public class NlpToArray {

        ArrayList <String> ner=new ArrayList <>();
        ArrayList <String> word=new ArrayList <>();
        ArrayList <String> normalizedNER=new ArrayList <>();
        ArrayList <String> ner1=new ArrayList <>();
        ArrayList <String> word1=new ArrayList <>();
        ArrayList <String> normalizedNER1=new ArrayList <>();
       public String [] diease;
      public String [] propability;
        String [] symtems;
    
    public void read2() 
    {
         try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("output.txt.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray)jsonObject.get("sentences");
            for(Object o: data){
                JSONObject jsonObject1 = (JSONObject) o;
                JSONArray data1 = (JSONArray)jsonObject1.get("tokens");
             for(Object o1: data1){
                JSONObject jsonObject2 = (JSONObject) o1;
                String word1 = (String)jsonObject2.get("word");
                String ner1 = (String)jsonObject2.get("ner");
                String normalize1 = (String)jsonObject2.get("normalizedNER");
                word.add(word1);
                ner.add(ner1);
                if(normalize1==null)
                {
                    normalizedNER.add("0");
                }
                else
                {
                    normalizedNER.add(normalize1);
                }
                }
            }
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

public void enhancemedicalner()
{
            int flag = 0;
            String x="";
for(int i=0;i<word.size();i++)
{ 
if(flag>1)
{
//System.err.println(i);
ner1.remove(i-flag+1);
normalizedNER1.remove(i-flag+1);
x=word1.get(i-flag)+" ";
x=x.concat(word1.get(i-flag+1));
word1.set(i-flag, x);
word1.remove(i-flag+1);

//flag--;

}

ner1.add(ner.get(i));
normalizedNER1.add(normalizedNER.get(i));
word1.add(word.get(i));


//System.out.println(i);

if((!"O".equals(ner.get(i)))&&(!"0".equals(normalizedNER.get(i))))
{

flag++;

}
else
{
flag=0;
}

}


}

public void remove_undefined_medical()
{
//    word1=word;
//    normalizedNER1=normalizedNER;
//    ner1=ner;
    int i=0,k=0,n;
    n=word1.size();
    while(n!=k)
    {
if(("O".equals(ner1.get(i)))||("DATE".equals(ner1.get(i)))||("0".equals(normalizedNER1.get(i))))
{
ner1.remove(i);
normalizedNER1.remove(i);
word1.remove(i);
}
else 
{
  i++;  
}
    k++;
    }    
}
    public void list_to_array()
    {
       diease=new String[ner1.size()];
       symtems=new String[word1.size()];
       propability=new String[normalizedNER1.size()];
       for(int i=0;i<word1.size();i++)
       {
           diease[i]=ner1.get(i);
           symtems[i]=word1.get(i);
           propability[i]=normalizedNER1.get(i);
           
       }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NlpToArray ob= new NlpToArray();
        ob.run(3);
       ob.read2();
      ob.enhancemedicalner();
       ob.remove_undefined_medical();
      ob.list_to_array();
//       System.err.println(ob.diease[0]+" s : "+ob.symtems[0]+" p : "+ob.propability[0]+" ");
//       System.err.println(ob.diease[1]+" s :"+ob.symtems[1]+" p : "+ob.propability[1]+" ");
//       System.err.println(ob.diease[2]+" s :"+ob.symtems[2]+" p :"+ob.propability[2]+" ");
//       System.err.println(ob.diease[3]+" s :"+ob.symtems[3]+" p :"+ob.propability[3]+" ");
         DiseaseDecisionMaker object =new DiseaseDecisionMaker();

       object.Decision(ob.diease, ob.propability);
        //JOptionPane.showMessageDialog(null, "helo", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void run(int x)
    {
    String command = null;
    if(x==1)
    {
        
         command = "./cmd"; //without adding our nlp
    }
     if(x==3)
    {
         command = "/home/elazab/Desktop/htdocs/cmd2"; // with adding our nlp
    }
      if(x==2)
    {
         command = "./cmd2"; // with adding our nlp
    }
      
       
       Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(NlpToArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Read the output
        BufferedReader reader =  
              new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        try {
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(NlpToArray.class.getName()).log(Level.SEVERE, null, ex);
        }

        try { 
            proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(NlpToArray.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
}
