import http from 'k6/http';
import { sleep, check } from 'k6';

/* =========================
   CONFIGURAZIONE TEST
========================= */
export const options = {
    stages: [
        //{ duration: '10s', target: 10 },   // salita
        { duration: '20s', target: 100 },   // carico
        //{ duration: '10s', target: 0 },    // discesa
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'],          // <1% errori
        http_req_duration: ['p(95)<300'],        // 95% < 300ms
    },
};

/* =========================
   SETUP: LOGIN (1 VOLTA)
========================= */
export function setup() {
    const loginPayload = JSON.stringify({
        username: 'admin',
        password: 'admin123',
    });

    const res = http.post(
        'http://localhost:8080/auth',
        loginPayload,
        {
            headers: { 'Content-Type': 'application/json' },
        }
    );

    check(res, {
        'login status 200': r => r.status === 200,
        'token present': r => r.json('token') !== undefined,
    });

    return {
        token: res.json('token'),
    };
}

/* =========================
   TEST PRINCIPALE
========================= */
export default function (data) {
    const params = {
        headers: {
            Authorization: `Bearer ${data.token}`,
            'Content-Type': 'application/json',
        },
    };

    const res = http.get(
        'http://localhost:8080/api/categorie',
        params
    );

    check(res, {
        'status is 200': r => r.status === 200,
        'response < 200ms': r => r.timings.duration < 200,
    });

    sleep(0.5); // simula comportamento umano
}
