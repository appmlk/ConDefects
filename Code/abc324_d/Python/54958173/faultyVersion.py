#4
#4320
N=int(input())
S=list(input())
S.sort()
ans=0
num1=1
num2=N
for i in S:
    if i=="0":
        num2-=1
while len(str(num1**2))<=len(S):
    s=list(str(num1**2))
    s.sort()
    num3=len(s)
    for i in s:
        if i=="0":
            num3-=1
    if num3==num2 and s==S[len(S)-len(s):]:
        ans+=1
    num1+=1
print(ans)