import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public final class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        final String TDash = scanner.next();
        final String[] S = new String[N];
        for (int i = 0; i < N; ++i) {
            S[i] = scanner.next();
        }

        scanner.close();

        final int TDashLength = TDash.length();

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            final int SiLength = S[i].length();
            boolean isLikely = true;

            if (Math.abs(TDashLength - SiLength) > 1) {
                continue;
            }

            if (TDashLength != SiLength) {
                final String stringLong, stringShort;
                if (TDashLength > SiLength) {
                    stringLong = TDash;
                    stringShort = S[i];
                } else {
                    stringShort = TDash;
                    stringLong = S[i];
                }

                int kShort = 0, kLong = 0;
                while (kShort < stringShort.length()) {
                    if (stringShort.charAt(kShort) == stringLong.charAt(kLong)) {
                        ++kShort;
                        ++kLong;
                        continue;
                    } else {
                        if (kShort == kLong) {
                            ++kLong;
                            continue;
                        } else {
                            isLikely = false;
                            break;
                        }
                    }
                }
            } else {
                int numberOfDifferentChars = 0;
                for (int j = 0; j < TDashLength; ++j) {
                    if (TDash.charAt(j) == S[i].charAt(j)) {
                        continue;
                    } else {
                        if (numberOfDifferentChars == 0) {
                            ++numberOfDifferentChars;
                            continue;
                        } else {
                            isLikely = false;
                            break;
                        }
                    }
                }
            }

            if (isLikely) {
                answer.add(i+1);
            }

        }

        System.out.println(answer.size());
        for (final int element : answer) {
            System.out.print(element);
            System.out.print(' ');
        }
        System.out.println();
    }
}
