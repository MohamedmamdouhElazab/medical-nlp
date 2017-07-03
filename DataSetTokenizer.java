package nlp.to.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSetTokenizer {

//    public static void main(String[] args) {
//
//        String csvFile = "dataset_raw.csv";
//        String line = "";
//        String cvsSplitBy = ",";
//        ArrayList<String[]> data = new ArrayList<String[]>();
//        //String x= data.get(0)[5];
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//            int i = 0;
//            while ((line = br.readLine()) != null) {
//
//                String[] LineData = line.split(cvsSplitBy);
//                //LineData[0] = LineData[0].replace(' ', '_');
//                //LineData[1] = LineData[1].replace(' ', '_');
//                data.add(i, LineData);
//                i++;
//
//                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//            }
//            br.close();
//            /*  BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));
//             for (String[] data1 : data) {
//             wr.write(data1[0]);
//             wr.write("  ");
//             wr.write(data1[1]);
//             wr.write("  ");
//             wr.write(data1[2]);
//             wr.write("\n");
//
//             }
//             wr.close();*/
//            String[] diseases = new String[data.size()];
//            String[] symptomps = new String[data.size()];
//            double[] weight = new double[data.size()];
//            int j = 0;
//            for (String[] data1 : data) {
//                if (data1.length != 3) {
//                    System.out.println(data1[0] + " " + data1[1] + " " + data1[2]);
//                }
//                diseases[j] = data1[0];
//                symptomps[j] = data1[1];
//                weight[j] = Double.parseDouble(data1[2]);
//                j++;
//            }
//            double num = weight[0];
//            int first = 0;
//            int last = 0;
//            for (int k = 1; k < weight.length; k++) {
//
//                if (k == weight.length - 1) {
//                    last = k;
//                    int len = last - first + 1;
//                    for (int l = first; l <= last; l++) {
//                        weight[l] = (weight[1] / num) / len;
//
//                    }
//                    first = k;
//                    last = k;
//                }
//                if (weight[k] == weight[first]) {
//                    num = weight[k];
//                    last = k;
//                } else {
//                    int len = last - first + 1;
//                    for (int l = first; l <= last; l++) {
//                        weight[l] = (weight[l] / num) / len;
//                    }
//                    first = k;
//                    last = k;
//                    num = weight[k];
//                }
//            }
//
//            /*for (int k = 0; k < weight.length; k++) {
//                System.out.println(weight[k]);
//            }*/
// DecimalFormat df = new DecimalFormat("#.####");
//            df.setRoundingMode(RoundingMode.CEILING);
//            
//            for (int k = 0; k < weight.length; k++) {
//                weight[k]=Double.parseDouble(df.format(weight[k]));
//            }
// /*String disease=diseases[0];
//                    wr.write(disease);
//                    wr.write("\n");
//            for (int k = 1; k < diseases.length; k++) {
//                if(!diseases[k].equals(disease))
//                {
//                wr.write(diseases[k]);
//               /* wr.write("  ");
//                wr.write(symptomps[k]);
//                wr.write("  ");
//                wr.write(Double.toString(weight[k]));
//                wr.write("\n");
//                disease=diseases[k];
//            
//                }
//            }*/
// 
// 
// 
//            BufferedWriter wr = new BufferedWriter(new FileWriter("xx.txt"));
//           
//            String disease = diseases[0];
//            int counter = 1;
//            wr.write(disease);
//            wr.write("\n");
//            wr.write(symptomps[0] + " => " + weight[0]);
//            wr.write("\n");
//            counter++;
//            do {
//                if (diseases[counter].equals(disease)) {
//                    wr.write(symptomps[counter] + " => " + weight[counter]);
//                    wr.write("\n");
//                } else {
//                    disease = diseases[counter];
//                    wr.write(disease);
//                    wr.write("\n");
//                    wr.write(symptomps[counter] + " => " + weight[counter]);
//                    wr.write("\n");
//
//                }
//                counter++;
//            } while (counter < diseases.length);
//            wr.write("\n");
//            
//            
//            ArrayList<String> checked = new ArrayList<String>();
//
//            for (int k = 0; k < diseases.length; k++) {
//                if (!checked.contains(symptomps[k])) {
//                    checked.add(symptomps[k]);
//                    ArrayList<Integer> repeated_symptomp ;
//                    repeated_symptomp = get_repeated_indices(symptomps, symptomps[k]);
//                    if(repeated_symptomp.size()>1)
//                    {
//                    for (int l = 0; l < repeated_symptomp.size(); l++) {
//                        wr.write(diseases[repeated_symptomp.get(l).intValue()]);
//                        if(l!=repeated_symptomp.size()-1)
//                            wr.write(",");
//                        else
//                        {
//                            wr.write("\n");
//                        }
//                    }
//                    wr.write(symptomps[k]+" => "+"\"");
//                    for (int o = 0; o < repeated_symptomp.size(); o++) {
//                        wr.write(Double.toString(weight[repeated_symptomp.get(o).intValue()]));
//                        if((o!=repeated_symptomp.size()-1))
//                            wr.write(",");
//                        else
//                        {
//                            wr.write("\""+"\n");
//                        }
//                    }
//                    }
//                }
//            }
//            wr.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static ArrayList<Integer> get_repeated_indices(String data[], String key) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(key)) {
                indices.add(i);
            }
        }
        return indices;
    }

}
