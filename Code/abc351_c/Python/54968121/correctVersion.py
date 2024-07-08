from collections import deque
def solve_C():
    n = int(input())
    a = list(map(int, input().split()))

    global dq
    dq = deque()

    def add(n):
        global dq
        if len(dq) == 0:
            dq.append(n)
            return
        
        most_left = dq.pop()
        if n == most_left:
            add(n+1)
        else:
            dq.append(most_left)
            dq.append(n)
            return
    
    i = 0
    while i < n-1:
        if a[i] == a[i+1] + 1 and a[n-1] == a[n-2]:
            i += 1
        else:
            break
    if i == n-2 and i != 0:
        print(1)
        return
    
    for num in a:
        add(num)

    print(len(dq))

solve_C()