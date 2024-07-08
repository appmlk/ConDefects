N = int(input())
S = [ord(s) - ord("a") for s in input()]
T = [ord(s) - ord("a") for s in input()]
Ns, Nt = len(S), len(T)
A = [[] for i in range(26)]
S = S + S  
for i in range(2 * Ns):
    A[S[i]].append(i)
    
for i in range(Nt):
    if len(A[T[i]]) == 0:
        print(0)
        exit()
        
C = [[0] * (2 * Ns) for i in range(26)]
Ac = [[0] * (2 * Ns + 1) for i in range(26)]
for i in range(2 * Ns):
    C[S[i]][i] = 1
    
for c in range(26):
    for i in range(2 * Ns):
        Ac[c][i + 1] = Ac[c][i] + C[c][i]
        
cnt = []
for i in range(26):
    cnt.append(Ac[i][-1]//2)
        
def check(m):
    now = 0
    v = 0
    for t in T:
        q = (m - 1)//cnt[t]
        r = m - q * cnt[t]
        v += q * Ns
        st = Ac[t][now]
        now = A[t][st + r - 1] + 1
        if now >= Ns:
            v += Ns
            now -= Ns
    v += now
    return v <= N * Ns

        
def BinarySearch(yes = 10 ** 18, no = -1):
    while abs(yes - no) != 1:
        mid = (yes + no)//2
        if check(mid):
            yes = mid
        else:
            no = mid
    return yes

yes = 0
no = 10 ** 18
print(BinarySearch(yes, no))