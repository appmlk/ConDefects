from itertools import product
from more_itertools import distinct_permutations

N = int(input())
R = list(input())
C = list(input())

di = {'A': [], 'B': [], 'C': []}
for p in distinct_permutations('ABC' + '.'*(N-3)):
    for x in p:
        if x != '.':
            di[x].append(p)
            break

ok_list = ['.'] * (N - 3) + ['A', 'B', 'C']

for vec in product(*[di[r] for r in R]):
    vec = [list(row) for row in vec]
    if any(sorted([vec[i][j] for i in range(N)]) != ok_list for j in range(N)):
        continue
    
    ng_flg = False
    
    for i in range(N):
        for j in range(N):
            if vec[i][j] != '.':
                if vec[i][j] != R[i]:
                    ng_flg = True
                break
    
    for j in range(N):
        for i in range(N):
            if vec[i][j] != '.':
                if vec[i][j] != C[j]:
                    ng_flg = True
                break
    
    
    if ng_flg:
        continue
    else:
        print('Yes')
        for row in vec:
            print(''.join(row))
        exit()

print('No')
