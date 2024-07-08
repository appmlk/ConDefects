from itertools import takewhile

_ = input()
first, *others = map(int, input().split())
ans = sum(1 for x in takewhile(lambda x: x <= first, others))
print(-1 if ans==len(others) else (ans+2))