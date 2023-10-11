"""
Author ankisho
Created 2023/09/10 22:47JST
"""

N,K = map(int,input().split())
A = list(map(int,input().split()))

nset = set()#すでに出現したあまり

ls = []
ncandy = 0
while True:
    idx = ncandy%N
    if idx in nset:
        cycle_st = idx
        break
    else:
        ls.append(idx)
        nset.add(idx)
    ncandy += A[idx]

#lsを周期前後で分ける
for i in range(len(ls)):
    if ls[i] == cycle_st:
        befcycle = ls[:i]
        aftcycle = ls[i:]

#周期前の飴の個数の累積和
befS = [0]*(len(befcycle)+1)
for i in range(len(befcycle)): befS[i+1]=befS[i]+A[befcycle[i]]

#周期入った後の飴の個数の累積和
aftS = [0]*(len(aftcycle)+1)
for i in range(len(aftcycle)): aftS[i+1]=aftS[i]+A[aftcycle[i]]

#print(befcycle,befS)
#print(aftcycle,aftS)

#答えの出力
ans = 0
ans +=befS[min(len(befcycle),K)]
ans +=aftS[len(aftcycle)]*((K-len(befcycle))//len(aftcycle))
ans +=aftS[max(K-len(befcycle),0)%len(aftcycle)]
print(ans)
