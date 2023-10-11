N=int(input())
p_array=list(map(lambda e: int(e)-1, input().split()))
q_array=list(map(lambda e: int(e)-1, input().split()))
rets=[0]*2*N
# pを先頭からチェック
done=[False]*2*N
now=0
for i in range(N*2):
    if p_array[i]==now:
        now+=1
        for j in range(now,N*2):
            if done[j]:
                now=j+1
            else:
                break
    else:
        rets[p_array[i]]=1
    done[p_array[i]]=True
#print(rets)
# pを末尾からチェック
done=[False]*2*N
now=2*N-1
for i in range(N*2-1,-1,-1):
    if p_array[i]==now:
        now-=1
        for j in range(now,-1,-1):
            if done[j]:
                now=j-1
            else:
                break
    else:
        if rets[p_array[i]]==1:
            print(-1)
            exit()
        rets[p_array[i]]=-1
    done[p_array[i]]=True
#print(rets)
# qを先頭からチェック
done=[False]*2*N
now=2*N-1
for i in range(N*2):
    if q_array[i]==now:
        now-=1
        for j in range(now,-1,-1):
            if done[j]:
                now=j-1
            else:
                break
    else:
        if rets[q_array[i]]==-1:
            print(-1)
            exit()
        rets[q_array[i]]=1
    done[q_array[i]]=True
#print(rets)
# qを末尾からチェック
done=[False]*2*N
now=0
for i in range(N*2-1,-1,-1):
    if q_array[i]==now:
        now+=1
        for j in range(now,N*2):
            if done[j]:
                now=j+1
            else:
                break
    else:
        if rets[q_array[i]]==1:
            print(-1)
            exit()
        rets[q_array[i]]=-1
    done[q_array[i]]=True
#print(rets)
if 0 in rets:
    print(-1)
    exit()

for ret in rets:
    if ret>0:
        print("(",end="")
    else:
        print(")",end="")
print()
