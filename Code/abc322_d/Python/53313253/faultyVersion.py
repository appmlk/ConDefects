import copy

def UP(M):
    New=[[0]*4 for i in range(4)]
    for i in range(3):
        New[i]=M[i+1].copy()
    return New

def DOWN(M):
    New=[[0]*4 for i in range(4)]
    for i in reversed(range(1,4)):
        New[i]=M[i-1].copy()
    return New

def LEFT(M):
    New=[[0]*4 for i in range(4)]
    for i in range(4):
        for j in range(3):
            New[i][j]=M[i][j+1]
    return New

def RIGHT(M):
    New=[[0]*4 for i in range(4)]
    for i in range(4):
        for j in reversed(range(1,4)):
            New[i][j]=M[i][j-1]
    return New

def ROT(M):
    New=[[0]*4 for i in range(4)]
    for i in range(4):
        for j in range(4):
            New[i][j]=M[j][-1-i]
    return New

def SUM(M):
    ret=0
    for i in range(4):
        for j in range(4):
            ret+=M[i][j]
    return ret

def OR(M1,M2,M3):
    ret=[[0]*4 for i in range(4)]
    for i in range(4):
        for j in range(4):
            ret[i][j]=M1[i][j]|M2[i][j]|M3[i][j]
    return ret

P1=[list(input()) for i in range(4)]
P2=[list(input()) for i in range(4)]
P3=[list(input()) for i in range(4)]

P1i=[[0]*4 for i in range(4)]
P2i=[[0]*4 for i in range(4)]
P3i=[[0]*4 for i in range(4)]

for i in range(4):
    for j in range(4):
        P1i[i][j]=int(P1[i][j]=="#")
        P2i[i][j]=int(P2[i][j]=="#")
        P3i[i][j]=int(P3[i][j]=="#")

P0=[]

P0.append(P1i.copy())
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))

P0.append(P2i.copy())
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))

P0.append(P3i.copy())
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))
P0.append(ROT(P0[-1].copy()))

P_init=[]
for M in P0:
    while True:
        New=UP(copy.deepcopy(M))
        if SUM(M)!=SUM(New):
            break
        M=New.copy()
    while True:
        New=LEFT(copy.deepcopy(M))
        if SUM(M)!=SUM(New):
            break
        M=New.copy()
    P_init.append(copy.deepcopy(M))

P1n=[]
for M1 in P_init[0:4]:
    M_init=copy.deepcopy(M1)
    for i in range(4):
        if SUM(M1)!=SUM(M_init):
            break
        M_test=copy.deepcopy(M_init)
        for j in range(4):
            if SUM(M_test)!=SUM(M_init):
                break
            P1n.append(M_test)
            M_test=RIGHT(copy.deepcopy(M_test))
        M_init=DOWN(copy.deepcopy(M_init))

P2n=[]
for M1 in P_init[4:8]:
    M_init=copy.deepcopy(M1)
    for i in range(4):
        if SUM(M1)!=SUM(M_init):
            break
        M_test=copy.deepcopy(M_init)
        for j in range(4):
            if SUM(M_test)!=SUM(M_init):
                break
            P2n.append(M_test)
            M_test=RIGHT(copy.deepcopy(M_test))
        M_init=DOWN(copy.deepcopy(M_init))

P3n=[]
for M1 in P_init[8:12]:
    M_init=copy.deepcopy(M1)
    for i in range(4):
        if SUM(M1)!=SUM(M_init):
            break
        M_test=copy.deepcopy(M_init)
        for j in range(4):
            if SUM(M_test)!=SUM(M_init):
                break
            P3n.append(M_test)
            M_test=RIGHT(copy.deepcopy(M_test))
        M_init=DOWN(copy.deepcopy(M_init))

for M1 in P1n:
    for M2 in P2n:
        for M3 in P3n:
            if SUM(OR(M1,M2,M3))==16:
                print("Yes")
                exit()
print("No")