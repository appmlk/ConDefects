def job():
    s = input()
    a_set = set()
    stack_kakko = []
    stack_i = []
    for i in range(len(s)):
        stack_i.append(i)
        c = s[i]
        if c == '(':
            stack_kakko.append(i)
        elif c == ')':
            peak = stack_kakko[-1]
            while stack_i[-1] > peak:
                idx = stack_i.pop()
                a_set.discard(s[idx])
        else:
            if c in a_set:
                exit(print('No'))
            else:
                a_set.add(c)
    print('Yes')


job()
