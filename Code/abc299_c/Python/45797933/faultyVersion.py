N=int(input())
S=input()

ans=0
flg=False
tmp=0
for i in range(N):
    if flg:
        if S[i]=="o":
            tmp+=1
        else:
            ans=max(ans,tmp)
            tmp=0
    else:
        if S[i]=="-":
            flg=True

flg=False
tmp=0
for i in range(N-1,-1,-1):
    if flg:
        if S[i]=="o":
            tmp+=1
        else:
            ans=max(ans,tmp)
            tmp=0
    else:
        if S[i]=="-":
            flg=True     

if ans==0:
    print(-1)
else:
    print(ans)