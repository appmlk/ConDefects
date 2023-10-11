N = int(input())
A = list(map(int, input().split()))

st = ['o' if A[i]%2 == (i+1)%2 else 'x' for i in range(N)]
ng = st.count(0)
ans = []

for i in range(N):
    flag = False
    if st[i] == 'o':
        q = [i]
        for j in range(i+2, N, 2):
            if st[j] == 'o':
                q.append(j)
            else:
                flag = True
                break
        if not flag:
            break
        while q:
            j = q.pop(-1)
            A[j], A[j+2] = A[j+2], A[j]
            st[j], st[j+2] = st[j+2], st[j]
            ans.append(f'B {j+1}')
                
for i in range(0, N, 2):
    if st[i] == 'o':
        break
    A[i], A[i+1] = A[i+1], A[i]
    ans.append(f'A {i+1}')

for i in range(N, 0, -1):
    p = A.index(i)
    for j in range(i, p-1, 2):
        A[j], A[j+2] = A[j+2], A[j]
        ans.append(f'B {j+1}')

print(len(ans))
for a in ans:
    print(a)

