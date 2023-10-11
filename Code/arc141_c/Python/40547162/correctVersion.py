n = int(input())
p = list(map(int,input().split()))
q = list(map(int,input().split()))

n2 = n*2
ans = [''] * (n2+1)
left = 0
max_pi = 0
max_qi = 0
for i,pi,qi in zip(range(n2), p, q[::-1]):
    max_pi = max(max_pi, pi)
    max_qi = max(max_qi, qi)

    # print(i, max_pi,max_qi)

    if i % 2 == 0:
        continue
    if max_pi != i+1 or max_qi != i+1:
        continue

    p_cut = p[left:i+1]
    q_cut = q[n2-i-1:n2-left]
    l = i+1-left
    # print(p_cut, q_cut)

    p_up = True
    for j in range(l-1):
        if p_cut[j] + 1 != p_cut[j+1]:
            p_up = False
            break
    
    if p_up:
        check = True
        for j in range(l-2):
            if q_cut[j] < q_cut[j+2]:
                check = False
        
        if check:           
            for j in range(l):
                if j % 2 == 0:
                    ans[q_cut[j]] = '('
                else:
                    ans[q_cut[j]] = ')'
            left = i+1
            continue

    q_down = True
    for j in range(l-1):
        if q_cut[j] - 1 != q_cut[j+1]:
            q_down = False
            break
    
    if q_down:
        check = True
        for j in range(l-2):
            if p_cut[j] > p_cut[j+2]:
                check = False

        if check:
            for j in range(l):
                if j % 2 == 0:
                    ans[p_cut[j]] = '('
                else:
                    ans[p_cut[j]] = ')'
            left = i+1
            continue

    print(-1)
    exit()

def check_ans(x, p, q):
    lefts = []
    rights = []
    for i,xi in enumerate(x,1):
        if xi == '(':
            lefts.append(i)
        else:
            rights.append(i)
    # print(x)
    # print(lefts,rights)
    
    p2 = []
    i,j = 0,0
    plus = 0
    while i < n or j < n:
        # print(i,j,plus)
        if i == n:
            p2.append(rights[j])
            j += 1
        elif j == n:
            p2.append(lefts[i])
            i += 1
        elif plus == 0:
            p2.append(lefts[i])
            i += 1
            plus += 1
        elif lefts[i] < rights[j]:
            p2.append(lefts[i])
            i += 1
            plus += 1
        else:
            p2.append(rights[j])
            j += 1
            plus -= 1
    
    if p != p2:
        return False
    
    q2 = []
    i,j = n-1,n-1
    plus = 0
    while i >= 0 or j >= 0:
        if i == -1:
            q2.append(rights[j])
            j -= 1
        elif j == -1:
            q2.append(lefts[i])
            i -= 1
        elif plus == 0:
            q2.append(lefts[i])
            i -= 1
            plus += 1
        elif lefts[i] > rights[j]:
            q2.append(lefts[i])
            i -= 1
            plus += 1
        else:
            q2.append(rights[j])
            j -= 1
            plus -= 1
    
    if q != q2:
        return False
    
    return True

if check_ans(ans[1:], p, q):
    print(*ans[1:], sep='')
else:
    print(-1)