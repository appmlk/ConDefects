N = int(input())
S = input()
ok = ["a", "b"]
for i in range(N-1):
    if S[i] in ok and S[i+1] in ok and S[i] != S[i+1]:
        print("Yes")
        exit()
print("No")