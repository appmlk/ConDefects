N = int(input())
S = input()

if S[0] == "B" and S[-1] == "A":
    print("No")
elif S == "BA":
    print("No")
else:
    print("Yes")
