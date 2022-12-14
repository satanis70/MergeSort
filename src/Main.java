import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Указаны не все аргументы..." );
            System.exit(0);
        } else if(args.length>2){
            System.out.println("Указаны лишние аргументы..." );
            System.exit(0);
        }
        ReadFile readFile = new ReadFile();
        ArrayList<Integer> firstListInteger, secondListInteger, finalListInteger;
        ArrayList<String> firstListStrings, secondListStrings, finalListStrings;
        CreateFile createFile = new CreateFile();
            if (Objects.equals(args[0], "-i")) {
                firstListInteger = readFile.readFileInteger("f1.txt");
                secondListInteger = readFile.readFileInteger("f2.txt");
                finalListInteger = firstListInteger;
                finalListInteger.addAll(secondListInteger);
                System.out.println(mergeSortInteger(finalListInteger));
                createFile.createFileInteger(args[1], mergeSortInteger(finalListInteger));
            } else if (Objects.equals(args[0], "-s")) {
                firstListStrings = readFile.readFileString("f3.txt");
                secondListStrings = readFile.readFileString("f4.txt");
                finalListStrings = firstListStrings;
                finalListStrings.addAll(secondListStrings);
                mergeSortString(finalListStrings, 0, finalListStrings.size()-1);
                System.out.println(finalListStrings);
                createFile.createFileStrings(args[1], finalListStrings);
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
