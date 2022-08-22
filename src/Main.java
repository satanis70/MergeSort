import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Указаны не все аргументы..." );
        }
        ReadFile readFile = new ReadFile();
        for (String arg : args) {
            if (Objects.equals(args[0], "-i")) {
                ArrayList<Integer> firstList, secondList, finalList;
                firstList = readFile.readFileInteger("f1.txt");
                secondList = readFile.readFileInteger("f2.txt");
                finalList = firstList;
                finalList.addAll(secondList);
                System.out.println(mergeSortInteger(finalList));
            } else if (Objects.equals(args[0], "-s")) {
                ArrayList<String> firstList, secondList, finalList;
                firstList = readFile.readFileString("f3.txt");
                secondList = readFile.readFileString("f4.txt");
                finalList = firstList;
                finalList.addAll(secondList);
                mergeSortString(finalList, 0, finalList.size()-1);
                System.out.println(finalList);
            }
        }
    }


    public static ArrayList<Integer> mergeSortInteger(ArrayList<Integer> array) {
        ArrayList<Integer> tmp;
        ArrayList<Integer> currentSrc = array;
        ArrayList<Integer> currentDest = new ArrayList<>(array);

        int size = 1;
        while (size < array.size()) {
            for (int i = 0; i < array.size() - 1; i += 2 * size) {
                mergeInteger(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }

            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;
        }
        return currentSrc;
    }

    private static void mergeInteger(ArrayList<Integer> src1, int src1Start, ArrayList<Integer> src2,
                                     int src2Start, ArrayList<Integer> dest, int destStart, int size) {
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.size());
        int src2End = Math.min(src2Start + size, src2.size());

        if (src1Start + size > src1.size()) {
            for (int i = src1Start; i < src1End; i++) {
                dest.set(i, src1.get(i));
            }
            return;
        }

        int iterationCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterationCount; i++) {
            if (index1 < src1End && (index2 >= src2End || src1.get(index1) < src2.get(index2))) {
                dest.set(i, src1.get(index1));
                index1++;
            } else {
                dest.set(i, src2.get(index2));
                index2++;
            }
        }
    }

    public static void mergeSortString(ArrayList<String> a, int from, int to) {
        if (from == to) {
            return;
        }
        int mid = (from + to) / 2;
        // sort the first and the second half
        mergeSortString(a, from, mid);
        mergeSortString(a, mid + 1, to);
        mergeString(a, from, mid, to);
    }
    public static void mergeString(ArrayList<String> a, int from, int mid, int to) {
        int n = to - from + 1;
        String[] b = new String[n];
        int i1 = from;
        int i2 = mid + 1;
        int j = 0;

        while (i1 <= mid && i2 <= to) {
            if (a.get(i1).compareTo(a.get(i2)) < 0) {
                b[j] = a.get(i1);
                i1++;
            } else {
                b[j] = a.get(i2);
                i2++;
            }
            j++;
        }

        while (i1 <= mid) {
            b[j] = a.get(i1);
            i1++;
            j++;
        }

        while (i2 <= to) {
            b[j] = a.get(i2);
            i2++;
            j++;
        }

        for (j = 0; j < n; j++) {
            a.set(from + j, b[j]);
        }
    }
}
