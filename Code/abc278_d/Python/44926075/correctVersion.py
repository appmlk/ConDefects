def main():
    n = int(input())
    a = list(map(int, input().split()))
    q = int(input())
    ans = {}
    tmp = {
        'value': -1,
        'list': set()
    }
    for _ in range(q):
        i = list(map(int, input().split()))
        if i[0] == 1:
            tmp['value'] = i[1]
            tmp['list'] = set()
        elif i[0] == 2:
            x, y = i[1]-1, i[2]
            aa = ans.get(x, a[x])
            if tmp['value'] == -1 or x in tmp['list']:
                ans[x] = aa + y
            else:
                ans[x] = tmp['value'] + y
                tmp['list'].add(x)
        elif i[0] == 3:
            x = i[1]-1
            aa = ans.get(x, a[x])
            if tmp['value'] == -1 or x in tmp['list']:
                print(aa)
            else:
                print(tmp['value'])

if __name__ == '__main__':
    main()