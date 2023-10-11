n=int(input())
mn=0
for q in range(1,n):
    print("?",mn+1,mn+1,q+1)
    s=input()
    if s=="Yes":
        mn=q
Ans=[0]
for q in range(1,n):
    ansa=0
    ansb=len(Ans)
    while ansa!=ansb:
        vid=ansa+(ansb-ansa)//2
        print("?",vid+1,mn+1,q+1)
        s=input()
        if s=="Yes":
            ansb=vid
        else:
            ansa=vid+1
    Ans=Ans[:ansa]+[q]+Ans[ansa:]
B=[]
for q in range(n):
    B.append(Ans.index(q)+1)
print("!",*B)