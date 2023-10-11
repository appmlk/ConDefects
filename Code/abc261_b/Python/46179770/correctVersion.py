n = int(input())
kama = [list(input()) for i in range(n)]
for i in range(n):
    for ipp in range(n):
        if kama[i][ipp] == "W":
            if kama[ipp][i] != "L":
                print("incorrect")
                exit()
        elif kama[i][ipp] == "D":
            if kama[ipp][i] != "D":
                print("incorrect")
                exit()
        elif kama[i][ipp] == "L":
            if kama[ipp][i] != "W":
                print("incorrect")
                exit()
print("correct")