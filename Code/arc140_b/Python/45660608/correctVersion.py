from sortedcontainers import SortedList
n=int(input())
s=[*input()]
cnts=[]
i=0
while i<n:
    if s[i]=="R":
        for j in range(1,n+5):
            if i-j<0 or s[i-j]!='A':
                cnta=j-1
                break
        for j in range(1,n+5):
            if i+j>=n or s[i+j]!='C':
                cntc=j-1
                break
        i+=cntc
        if min(cnta,cntc)!=0:
            cnts.append(min(cnta,cntc))
    i+=1
stl=SortedList(cnts)
ans=0
while stl:
    tmp=stl.pop()
    if tmp-1!=0:stl.add(tmp-1)
    ans+=1
    if not stl:break
    stl.pop(0)
    ans+=1
print(ans)