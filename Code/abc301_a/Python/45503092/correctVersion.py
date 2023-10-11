N = int(input())
S = input()

T_W = S.count('T')
A_W = S.count('A')
print("T" if T_W > A_W or T_W == A_W and S[-1] == "A" else "A")