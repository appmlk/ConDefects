import heapq
import collections
N = int(input())
AB = [list(map(int, input().split())) for _ in range(N)]
OK = []
NG = []
for a,b in AB:
    if a>=b:
        OK.append([a,b])
    else:
        NG.append([a,b])
OK = sorted(OK, reverse=True, key=lambda x: x[0])
OK_D = collections.deque(OK)
NG_A = []
NG_B = []
for a,b in NG:
    NG_A.append(a)
    NG_B.append(b)
NG_A.sort(reverse=True)
NG_A = collections.deque(NG_A)
H_NG_B = []
for b in NG_B:
    heapq.heappush(H_NG_B,-b)
H_OK_B = []
ans = len(OK)
while NG_A:
    while len(OK_D) and OK_D[0][0]>=-H_NG_B[0]:
        a,b = OK_D.popleft()
        heapq.heappush(H_OK_B,b)
    if len(H_NG_B) and -H_NG_B[0]<=NG_A[0]:
        NG_A.popleft()
        heapq.heappop(H_NG_B)
    elif len(H_OK_B):
        ans-=1        
        b = heapq.heappop(H_OK_B)
        heapq.heappush(H_NG_B,-b)
        heapq.heappop(H_NG_B)
    else:
        print(-1)
        exit()
print(ans)

