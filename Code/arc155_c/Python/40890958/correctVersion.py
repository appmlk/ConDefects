N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))


def sort_data(data):
    odd_pos = []
    for i, a in enumerate(data):
        if a % 2:
            odd_pos.append(i)
    
    flag = 0
    for i1, i2 in zip(odd_pos, odd_pos[1:]):
        if i2-i1 <= 2:
            flag = 1
            break
    
    if flag and len(odd_pos)<len(data):
        odd = []
        even = []
        for a in data:
            if a % 2:
                odd.append(a)
            else:
                even.append(a)
        odd.sort()
        if len(even) >= 3:
            even.sort()
        res = odd + even
    else:
        res = []
        fr = 0
        for i in odd_pos:
            if fr != i:
                if i - fr >= 3:
                    res += sorted(data[fr:i])
                else:
                    res += data[fr:i]
            res.append(data[i])
            fr = i + 1
        if data[-1] % 2 == 0:
            if len(data[fr:]) >= 3:
                res += sorted(data[fr:])
            else:
                res += data[fr:]
    return res

A = sort_data(A)
B = sort_data(B)

if A == B:
    print('Yes')
else:
    print('No')