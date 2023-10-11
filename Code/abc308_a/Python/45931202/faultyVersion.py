S = list(map(int,input().split()))
for i in range(len(S)-1):
    if S[i] >= S[i+1] or S[i] % 25 or S[i] < 100 or S[i] > 675:
        print("No")
        exit()

if S[-1] % 25 or S[-1] < 100 or S[-1] > 675:
    print("No")
else: print("Yes")

