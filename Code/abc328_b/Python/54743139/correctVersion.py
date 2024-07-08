N=int(input())
D=input().split()
ans=0
for Mon in range(1,N+1):
  Mlist=list(str(Mon))
  for Day in range(1,int(D[Mon-1])+1):
    Dlist=list(str(Day))
    
    if len(Mlist)==1 and len(Dlist)==1 and Mlist[0]==Dlist[0]:
      ans+=1
      # print(Mon,Day)
    if len(Mlist)==1 and len(Dlist)==2 and Mlist[0]==Dlist[0]==Dlist[1]:
      ans+=1
      # print(Mon,Day)
    if len(Mlist)==2 and len(Dlist)==1 and Mlist[0]==Mlist[1]==Dlist[0]:
      ans+=1
      # print(Mon,Day)
    if len(Mlist)==2 and len(Dlist)==2 and Mlist[0]==Mlist[1]==Dlist[0]==Dlist[1]:
      ans+=1
      # print(Mon,Day)
      
    


print(ans)