from itertools import groupby
S = input()
T = input()

def runLengthEncode(S: str) -> "list[tuple(str, int)]":
    grouped = groupby(S)
    res = []
    for k, v in grouped:
        res.append((k, int(len(list(v)))))
    return res

SS = runLengthEncode(S)
TT = runLengthEncode(T)

if len(SS) != len(TT):
    print('No')
    exit()
else:
    for i in range(len(SS)):
        if SS[i][0] != TT[i][0]:
            print('No')
            exit()
        else:
            if SS[i][1] == 1 and TT[i][1] > 1:
                print('No')
                exit()

print('Yes')