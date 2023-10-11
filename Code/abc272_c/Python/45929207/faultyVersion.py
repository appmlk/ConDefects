N = int(input())
A = list(map(int, input().split()))

odd = [x for x in A if x%2==1]
even = [x for x in A if x%2==0 and x != 0]
odd.sort(reverse=True)
even.sort(reverse=True)
if len(odd) < 2 and len(even) < 2:
    print(-1)
elif len(odd) < 2:
    print(even[0]+even[1])
elif len(even) < 2:
    print(odd[0]+odd[1])
else:
    print(max(even[0]+even[1], odd[0]+odd[1]))
