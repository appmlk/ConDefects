MOD = 998244353


def solve(h, w, s_list):
    count_y = 0
    for s in s_list:
        count_y += s.count("Y")
    if count_y % 2 == 1:
        return 0
    count_y //= 2
    candidates = []
    for i in range(1, 2002):
        if i * i > count_y:
            break
        if count_y % i == 0:
            candidates.append(i)
            if i * i < count_y:
                candidates.append(count_y // i)
    # 2-dim cum sum
    count_y_cum = [[0] * w for _ in range(h)]
    if s_list[0][0] == "Y":
        count_y_cum[0][0] = 1
    else:
        count_y_cum[0][0] = 0
    for i in range(1, h):
        if s_list[i][0] == "Y":
            count_y_cum[i][0] = count_y_cum[i - 1][0] + 1
        else:
            count_y_cum[i][0] = count_y_cum[i - 1][0]
    for j in range(1, w):
        if s_list[0][j] == "Y":
            count_y_cum[0][j] = count_y_cum[0][j - 1] + 1
        else:
            count_y_cum[0][j] = count_y_cum[0][j - 1]
    for i in range(1, h):
        for j in range(1, w):
            count_y_cum[i][j] = count_y_cum[i][j - 1] + count_y_cum[i - 1][j] - count_y_cum[i - 1][j - 1]
            if s_list[i][j] == "Y":
                count_y_cum[i][j] += 1

    res = 0
    for p in candidates:
        q = count_y // p
        if p > h or q > w:
            continue
        r = 1
        free = 0
        target = 0
        h_list = []
        for i in range(h):
            if count_y_cum[i][w - 1] == target:
                free += 1
            elif count_y_cum[i][w - 1] == target + 2 * q:
                if 0 < target < 2 * count_y - 2 * q:
                    r *= free
                    r %= MOD
                target += 2 * q
                free = 1
                h_list.append(i)

        free = 0
        target = 0
        w_list = []
        for j in range(w):
            if count_y_cum[h - 1][j] == target:
                free += 1
            elif count_y_cum[h - 1][j] == target + 2 * p:
                if 0 < target <= 2 * count_y - 2 * p:
                    r *= free
                    r %= MOD
                target += 2 * p
                free = 1
                w_list.append(j)
        # print(p, q)
        # print(h_list)
        # print(w_list)
        if len(h_list) != p or len(w_list) != q:
            continue

        # check
        flag = 1
        for i, h_ in enumerate(h_list):
            for j, w_ in enumerate(w_list):
                if count_y_cum[h_][w_] != (i + 1) * (j + 1) * 2:
                    flag = 0

        if flag == 1:
            # print(p, q, r)
            res += r
            res %= MOD

    return res


def main():
    h, w = map(int, input().split())
    s_list = [input() for _ in range(h)]
    res = solve(h, w, s_list)
    print(res)


def test():
    assert solve(2, 3, ["XYY", "YXY"]) == 2
    assert solve(2, 3, ["XYX", "YYY"]) == 0
    assert solve(2, 58, [
        "YXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXY",
        "YXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXYXXY"
    ]) == 164036797


if __name__ == "__main__":
    test()
    main()
