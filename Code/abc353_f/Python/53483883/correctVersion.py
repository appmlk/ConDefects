K = int(input())
S = list(map(int, input().split()))
T = list(map(int, input().split()))

def dist_big(S, T):
    global K
    if K <= 2:
        D = [abs(S[i] - T[i]) for i in range(2)]
        ret = min(D) * 2
        ret += (max(D) - min(D)) * (K + 1) // 2
        return ret
    else:
        return abs((S[0] + S[1]) - (T[0] + T[1])) + abs((S[0] - S[1]) - (T[0] - T[1]))

def dist(S, T):
    global K
    SB = [(s // K) for s in S]
    TB = [(t // K) for t in T]
    if SB == TB and sum(SB) % 2 == 1:
        return 0

    if sum(SB) % 2 == 0:
        Sn = 4
        Sout = [-(S[0]+1)%K + 1, S[0]%K + 1, -(S[1]+1)%K + 1, S[1]%K + 1]
        SB = [list(SB) for _ in range(Sn)]
        SB[0][0] += 1
        SB[1][0] -= 1
        SB[2][1] += 1
        SB[3][1] -= 1
    else:
        SB = [SB]
        Sout = [0]
        Sn = 1
    
    if sum(TB) % 2 == 0:
        Tn = 4
        Tout = [-(T[0]+1)%K + 1, T[0]%K + 1, -(T[1]+1)%K + 1, T[1]%K + 1]
        TB = [list(TB) for _ in range(Tn)]
        TB[0][0] += 1
        TB[1][0] -= 1
        TB[2][1] += 1
        TB[3][1] -= 1
    else:
        TB = [TB]
        Tout = [0]
        Tn = 1
    
    D = [[dist_big(SB[si], TB[ti]) + Sout[si] + Tout[ti] for si in range(Sn)] for ti in range(Tn)]
    ret = min(min(D[ti]) for ti in range(Tn))
    if ret > abs(S[0] - T[0]) + abs(S[1] - T[1]): ret = abs(S[0] - T[0]) + abs(S[1] - T[1])
    return ret

print(dist(S, T))
