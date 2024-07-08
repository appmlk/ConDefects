## https://atcoder.jp/contests/abc294/tasks/abc294_g

from collections import deque

class BinaryIndexTree:
    """
    フェニック木(BinaryIndexTree)の基本的な機能を実装したクラス
    """
    def __init__(self, size):
        self.size = size
        self.array = [0] * (size + 1)
    
    def add(self, x, a):
        index = x
        while index <= self.size:
            self.array[index] += a
            index += index & (-index)
    
    def sum(self, x):
        index = x
        ans = 0
        while index > 0:
            ans += self.array[index]
            index -= index & (-index)
        return ans

    def least_upper_bound(self, value):
        if self.sum(self.size) < value:
            return -1
        elif value <= 0:
            return 0

        m = 1
        while m < self.size:
            m *= 2

        k = 0
        k_sum = 0
        while m > 0:
            k0 = k + m
            if k0 < self.size:
                if k_sum + self.array[k0] < value:
                    k_sum += self.array[k0]
                    k += m
            m //= 2
        if k < self.size:
            return k + 1
        else:
            return -1

class SegmentTree:
    """
    非再帰版セグメント木。
    更新は「加法」、取得は「最大値」のもの限定。
    """

    def __init__(self, init_array):
        n = 1
        while n < len(init_array):
            n *= 2
        
        self.size = n
        self.array = [(float("inf"), float("inf")) for _ in range(2 * self.size)]
        for i, a in enumerate(init_array):
            self.array[self.size + i] = (a, i)
        
        end_index = self.size
        start_index = end_index // 2
        while start_index >= 1:
            for i in range(start_index, end_index):
                self.array[i] = min(self.array[2 * i], self.array[2 * i + 1])
            end_index = start_index
            start_index = end_index // 2

    def add(self, x, a):
        index = self.size + x
        self.array[index] += a
        while index > 1:
            index //= 2
            self.array[index] = min(self.array[2 * index], self.array[2 * index + 1])

    def get_min(self, l, r):
        L = self.size + l; R = self.size + r

        # 2. 区間[l, r)の最大値を求める
        s = (float("inf"), float("inf"))
        while L < R:
            if R & 1:
                R -= 1
                s = min(s, self.array[R])
            if L & 1:
                s = min(s, self.array[L])
                L += 1
            L >>= 1; R >>= 1
        return s


def main():
    N = int(input())
    next_nodes = [[] for _ in range(N)]
    for i in range(N -  1):
        u, v, w = map(int, input().split())
        u -= 1
        v -= 1
        next_nodes[u].append((v, w, i))
        next_nodes[v].append((u, w, i))
    
    Q = int(input())
    queries = []
    for _ in range(Q):
        values = tuple(map(int, input().split()))
        queries.append(values)
    
    # オイラーツアー
    queue = deque()
    queue.append((0, 0))
    index_array = [-1] * N
    index_array[0] = 0

    parents = [-2] * N
    parents[0] = -1
    euler_tour_node = [0]
    euler_tour_edge = [0]
    edge_index_to_eluer_tour_index = {}

    while len(queue) > 0:
        v, index = queue.pop()
        if index > 0:
            euler_tour_node.append(index_array[v])
            next_node, w, edge_index = next_nodes[v][index - 1]
            euler_tour_edge.append(-w)
            edge_index_to_eluer_tour_index[edge_index].append(len(euler_tour_edge) - 1)


        while index < len(next_nodes[v]):
            next_node, w, edge_index = next_nodes[v][index]
            if next_node == parents[v]:
                index += 1
                continue

            parents[next_node] = v
            index_array[next_node] = len(euler_tour_node)
            euler_tour_node.append(len(euler_tour_node))
            euler_tour_edge.append(w)
            edge_index_to_eluer_tour_index[edge_index] = []
            edge_index_to_eluer_tour_index[edge_index].append(len(euler_tour_edge) - 1)

            queue.append((v, index + 1))
            queue.append((next_node, 0))
            break
        
    # euler_tour_edgeの累積和を取る
    bit = BinaryIndexTree(len(euler_tour_edge))
    for i in range(len(euler_tour_edge)):
        bit.add(i + 1, euler_tour_edge[i])
    
    # セグメント木
    seg_tree = SegmentTree(euler_tour_node)

    # クエリの対応
    for query in queries:
        if query[0] == 1:
            _, i, w = query
            i -= 1
            index1, index2 = edge_index_to_eluer_tour_index[i]
            old_w = euler_tour_edge[index1]
            bit.add(index1 + 1, w - old_w)
            euler_tour_edge[index1] = w

            old_w = euler_tour_edge[index2]
            bit.add(index2 + 1, -w - old_w)
            euler_tour_edge[index2] = w

        else:
            _, u, v = query
            u -= 1
            v -= 1
            ind_u = index_array[u]
            ind_v = index_array[v]
            if ind_u > ind_v:
                u, v = v, u
                ind_u, ind_v = ind_v, ind_u
            _, j = seg_tree.get_min(ind_u, ind_v + 1)
            ans1 = bit.sum(ind_u + 1) - bit.sum(j + 1)
            ans2 = bit.sum(ind_v + 1) - bit.sum(j + 1)
            print(ans1 + ans2)






   
if __name__ == "__main__":
    main()
