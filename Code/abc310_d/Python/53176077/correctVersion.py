N,T,M=map(int,input().split())
#たかだか10人しかいません
hate=set()

for _ in range(M):
    a,b=map(lambda x:int(x)-1,input().split())
    hate.add((a,b))
    hate.add((b,a))
    

def f(now):
    if now==N:
        #nowをNから波及させていくが、そのとき、ちゃんとteamsがTになっていれば1を返すようにする。
        return 1 if len(teams)==T else 0

    ans=0
    
    for i in range(len(teams)):
        if len(teams[i])>0:
            for t in teams[i]:
                if (now,t) in hate:
                    #相性悪い人がいる場合
                    break
            else:
                #いない場合
                teams[i].add(now)
                ans+=f(now+1)
                teams[i].remove(now)
                
    if len(teams)<T:
        teams.append(set([now]))
        ans+=f(now+1)
        teams.pop()
        
    return ans

teams=[]
print(f(0))