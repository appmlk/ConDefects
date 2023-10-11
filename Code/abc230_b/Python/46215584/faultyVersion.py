S=input()
if len(S)<3:
    if S in "oxxox":
        print("Yes")
    else:
        print("No")
else:
    cnt=0
    ans=True
    for i in range(len(S)):
        if S[i]=="x":
            cnt+=1
        elif S[i]=="o":
            if cnt==2:
                cnt=0
            elif cnt==i:
                cnt=0
            else:
                ans=False
                cnt=0
    if ans:
        print("Yes")
    else:
        print("No")
                
            