from itertools import product
import copy
import sys
sys.setrecursionlimit(1 << 20)

N,H,W = map(int, input().split())
AB = list()

for _ in range(N):
    a,b = map(int, input().split())
    AB.append([a,b])
global ans
ans = False
C = [ [True for _ in range(W)] for i in range(H)]
check = [0,1,0,1,1]

def dfs(x,y,rem,masu):
    global ans
    R = copy.deepcopy(rem)
    M = copy.deepcopy(masu)
    flag = True
    #if bits==check:
    #    print("############")
    #if x==H and y==W:
        #print("##########")
        #print(bits)
        #for i in range(H):
    #    print(M[i])
    if C==M:
        #print(bits)
        ans = True
        return
    if y==W:
        for i in range(H):
            flag = False
            for j in range(W):
                if M[i][j] == False:
                    x = i
                    y = j
                    flag = True
                    break
            if flag:
                break
    """
    if bits==check:
        print("#")
        print(x,y)
        print(R)
        for i in range(H):
            print(M[i])
    """
    for i in range(len(R)):
        #if bits==check:
        #    print("#####")
        #    print(x,y,R[i][0],R[i][1])
        #    print(x+R[i][0],y+R[i][1])
        if x+R[i][0]<=H and y+R[i][1]<=W:
            #if bits==check:
            #    print("##")
            R_tmp = copy.deepcopy(R)
            R_tmp.remove(R_tmp[i])
            M_tmp = copy.deepcopy(M)
            flag = True
            for a in range(x,x+R[i][0]):
                for b in range(y,y+R[i][1]):
                    if M_tmp[a][b]==True:
                        flag = False
                    M_tmp[a][b] = True
            if flag==True:
                dfs(x, y+R[i][1], R_tmp, M_tmp)

        if x+R[i][1]<=H and y+R[i][0]<=W:
            #if bits==check:
            #    print("###")
            R_tmp = copy.deepcopy(R)
            R_tmp.remove(R_tmp[i])
            M_tmp = copy.deepcopy(M)
            flag = True
            for a in range(x,x+R[i][1]):
                for b in range(y,y+R[i][0]):
                    if M_tmp[a][b]==True:
                        flag = False
                    M_tmp[a][b] = True
            if flag==True:
                dfs(x, y+R[i][0], R_tmp, M_tmp)

for bits in product([0,1],repeat=N):
    bits = list(bits)
    #print(bits)
    item = list()
    for i in range(N):
        if bits[i]:
            item.append(AB[i])
    masu = [ [False for _ in range(W)] for j in range(H)]
    csum = 0
    for p in item:
        csum += p[0]
    if csum != H*W:
        continue
    dfs(0,0,item,masu)

if ans:
    print("Yes")
else:
    print("No")