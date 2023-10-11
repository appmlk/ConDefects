N = int(input())
S=input()
ck=0
if S=="BA":
    exit(print("No"))
for i in range(N):
    if S[i]!=S[-1-i]:
        ck=1
if ck==0:
    exit(print("Yes"))
if S[0]=="A" and S[-1]=="B":
    exit(print("No"))
print("Yes")