S = list(input())
x = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
if len(S) != 8:
    print("No")
    exit()
elif not(S[0] in x) and not(S[-1] in x) and S[1] != "0":
    for i in range(6):
        if not(S[i + 1] in x):
            print("No")
            exit()
else:
    print("No")
    exit()
print("Yes")