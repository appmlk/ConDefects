S = input()
C = S[0]
D = S[1:]
ans = True
if C.isupper() and (len(S) == 1 or D.islower()):
    print("Yes")
else:
    print("No")