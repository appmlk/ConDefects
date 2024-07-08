def job():
    n, a, b = map(int, input().split())
    s = input()
    a_sum = 0
    a_min = 0
    a = min(a, 2 * b)
    left = s.count(')')
    right = s.count('(')
    s = list(s)
    ans = 0
    diff = abs(right - left)
    cnt = diff // 2
    ans += b * cnt
    tot = 0
    if left > right:
        for i in range(2 * n):
            if tot == cnt:
                break
            c = s[i]
            if c == ')':
                s[i] = '('
                tot += 1
    else:
        for i in reversed(range(2 * n)):
            if tot == cnt:
                break
            c = s[i]
            if c == '(':
                s[i] = ')'
                tot += 1
    for c in s:
        if c == '(':
            a_sum += 1
        else:
            a_sum -= 1
        a_min = min(a_sum, a_min)
    swap_count = abs(a_min // 2)
    if abs(a_min) % 2 == 1:
        swap_count += 1

    ans += a * swap_count
    print(ans)


job()
