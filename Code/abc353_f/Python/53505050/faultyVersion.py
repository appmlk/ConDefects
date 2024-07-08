K=int(input())
S=list(map(int,input().split()))
T=list(map(int,input().split()))

S[0]+=2*K
S[1]+=2*K
T[0]+=2*K
T[1]+=2*K


distX = T[0] - S[0]
distY = T[1] - S[1]
bigM = max(distX,-distX) + max(distY,-distY)

if K == 1:
    print(bigM)
    exit()




if (S[0]//K + S[1]//K) %2 == 0:
    tileS = "small"
else:
    tileS = "large"


if (T[0]//K + T[1]//K) %2 == 0:
    tileT = "small"
else:
    tileT = "large"

lstS=[]
lstT=[]

if tileS == "large":
    lstS.append([S,0])
else:
    lstS.append([[S[0] - K,S[1]],S[0] - K* (S[0]//K) +1 ])
    lstS.append([[S[0] + K,S[1]], K * (S[0]//K + 1) - S[0]])
    lstS.append([[S[0],S[1] - K], S[1] - K* (S[1]//K) +1])
    lstS.append([[S[0],S[1] + K], K*(S[1]//K+1) - S[1]])

if tileT == "large":
    lstT.append([T,0])
else:
    lstT.append([[T[0] - K,T[1]],T[0] - K* (T[0]//K) +1])
    lstT.append([[T[0] + K,T[1]], K * (T[0]//K + 1) - T[0]])
    lstT.append([[T[0],T[1] - K], T[1] - K* (T[1]//K) +1 ])
    lstT.append([[T[0],T[1] + K], K*(T[1]//K+1) - T[1]])

res = bigM
#print(lstS,lstT)
for ss in lstS:
    s,dist0 = ss
    if dist0 < 0:
        dist0 *= -1
    blS = [ K * (s[0]//K), K * (s[1]//K)]
    for tt in lstT:
        t,dist1 = tt
        if dist1 < 0:
            dist1 *= -1
        dist1
        blT = [K*(t[0]//K),K*(t[1]//K)]

        blockX = (blT[0] - blS[0]) // K
        blockY = (blT[1] - blS[1]) // K
        dist = 2 * max(blockX,blockY, -blockX, -blockY) + dist0 + dist1
        if dist < res:
            #print("#",dist0,dist1,blockX,blockY,blS,blT,s,t)
            res = dist
        if K == 2:
            blockX = max(blockX,-blockX)
            blockY = max(blockY,-blockY)
            if blockX > blockY:
                blockX,blockY = blockY, blockX
            dist = 2 * blockX + 3 * (blockY - blockX) + dist0 + dist1
            if dist < res:
                res = dist

print(res)

