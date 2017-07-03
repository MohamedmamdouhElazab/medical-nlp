/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.to.array;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Muhamed Mahrous
 */
public class DiseaseDecisionMaker {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        String x[] = {"x,y,z,b", "b"};
//        String a[] = {"0.2,0.1,0.5,0.6", "0.4"};
//        
//      //  Decision(x, a);
//    }
    public void Decision(String Diseases[], String Probs[]) {

        ArrayList<String> tokenized_diseases = new ArrayList<>();
        ArrayList<String> tokenized_probs = new ArrayList<>();
        String diseases, probabilities;
        for (int i = 0; i < Diseases.length; i++) {
            diseases = Diseases[i];
            String[] list_of_diseases;
            list_of_diseases = diseases.split(",");
            for (int j = 0; j < list_of_diseases.length; j++) {
                tokenized_diseases.add(list_of_diseases[j]);
            }
            probabilities = Probs[i];
            String[] list_of_probabilities;
            list_of_probabilities = probabilities.split(",");
            for (int j = 0; j < list_of_probabilities.length; j++) {
                tokenized_probs.add(list_of_probabilities[j]);
            }
        }
        Double[] pro = new Double[tokenized_probs.size()];
        for (int i = 0; i < tokenized_probs.size(); i++) {
            pro[i] = Double.parseDouble(tokenized_probs.get(i));
        }
        ArrayList<String> checked = new ArrayList<>();
        ArrayList<String> Result = new ArrayList<>();
        ArrayList<Double> final_probabilities = new ArrayList<>();

        for (int l = 0; l < tokenized_diseases.size(); l++) {
            if (!checked.contains(tokenized_diseases.get(l))) {
                checked.add(tokenized_diseases.get(l));
                ArrayList<Integer> repeated;
                repeated = DataSetTokenizer.get_repeated_indices(tokenized_diseases.toArray(new String[tokenized_diseases.size()]), tokenized_diseases.get(l));
                String res = tokenized_diseases.get(l);
                double percentage = 0;
                for (int p = 0; p < repeated.size(); p++) {
                    percentage += Double.parseDouble(tokenized_probs.get(repeated.get(p)));
                }

                final_probabilities.add(percentage);
                res += "  " + Double.toString(percentage);
                //  System.out.println(res);
            } else {
                continue;
            }

        }
        double max = 0;
        int max_index=0;
        for (int i = 0; i < checked.size(); i++) {
            if (final_probabilities.get(i) > max) {
                max = final_probabilities.get(i);
                max_index = i;
            }
            System.out.println(checked.get(i) + " " + 100 * final_probabilities.get(i) + "%");
        }
        System.out.println("Most probably "+checked.get(max_index)+" "+100*final_probabilities.get(max_index)+"%");
        JOptionPane.showMessageDialog(null, "Most probably you are diagnozed with"+checked.get(max_index)+"with probability"+100*final_probabilities.get(max_index)+"%", "medical status: ", JOptionPane.INFORMATION_MESSAGE);
    }

}
