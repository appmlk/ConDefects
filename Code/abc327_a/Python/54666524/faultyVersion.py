N = int(input())
S = input()
ok = ["a", "b"]
for i in range(N-1):
    if S[i] in ok and S[i+1] in ok:
        print("Yes")
        exit()
print("No")